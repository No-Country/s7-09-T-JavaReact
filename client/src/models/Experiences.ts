export type Experiences = Experience[]

export interface Experience {
  id: number
  title: string
  subtitle?: string
  description?: string
  images: Image[]
  category?: Category
  city?: City
  address?: string
  averageScore: number
  latitude?: number
  longitude?: number
}

export interface Image {
  id: number
  url: string
  alt: string
}

export interface Category {
  id: number
  title: string
  icon: string
  tags: Tag[]
}

export interface Tag {
  id: number
  title: string
  icon: string
}

export interface City {
  id: number
  city: string
  province: string
  country: string
}
